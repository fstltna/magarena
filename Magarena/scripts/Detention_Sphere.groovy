def filter = new MagicPermanentFilterImpl() {
    public boolean accept(final MagicSource source,final MagicPlayer player,final MagicPermanent target) {
        return target.isLand() == false && target.isName("Detention Sphere") == false;
    }
};

def choice = new MagicTargetChoice(filter, "target nonland permanent not named Detention Sphere");

[
    new EntersBattlefieldTrigger() {
        @Override
        public MagicEvent executeTrigger(final MagicGame game,final MagicPermanent permanent, final MagicPayedCost payedCost) {
            return new MagicEvent(
                permanent,
                choice,
                MagicExileTargetPicker.create(),
                this,
                "Exile target nonland permanent\$ not named Detention Sphere and all other permanents with the same name as that permanent."
            );
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            event.processTargetPermanent(game, {
                new MagicNameTargetFilter(it.getName()).filter(event) each {
                    game.doAction(new ExileLinkAction(
                        event.getPermanent(),
                        it
                    ));
                }
            });
        }
    }
]
