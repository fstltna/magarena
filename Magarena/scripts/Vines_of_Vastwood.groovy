[
    new MagicSpellCardEvent() {
        @Override
        public MagicEvent getEvent(final MagicCardOnStack cardOnStack,final MagicPayedCost payedCost) {
            return new MagicEvent(
                cardOnStack,
                TARGET_CREATURE,
                MagicPumpTargetPicker.create(),
                this,
                "Target creature\$ can't be the target of spells or abilities PN's opponents control this turn. " +
                "If SN was kicked, that creature gets +4/+4 until end of turn."
            );
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            event.processTargetPermanent(game, {
                game.doAction(new GainAbilityAction(
                    it,
                    MagicAbility.CannotBeTheTarget(event.getPlayer().getOpponent())
                ));
                if (event.isKicked()) {
                    game.doAction(new ChangeTurnPTAction(it,4,4));
                }
            });
        }
    }
]
