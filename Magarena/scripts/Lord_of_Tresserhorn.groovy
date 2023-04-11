[
    new EntersBattlefieldTrigger() {
        @Override
        public MagicEvent executeTrigger(final MagicGame game, final MagicPermanent permanent, final MagicPayedCost payedCost) {
            return new MagicEvent(
                permanent,
                TARGET_OPPONENT,
                this,
                "PN loses 2 life, PN sacrifices two creatures, and target opponent\$ draws two cards."
            )
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            event.processTargetPlayer(game, {
                game.doAction(new ChangeLifeAction(event.getPlayer(),-2));
                game.addEvent(new MagicSacrificePermanentEvent(
                    event.getSource(),
                    SACRIFICE_CREATURE
                ));
                game.addEvent(new MagicSacrificePermanentEvent(
                    event.getSource(),
                    SACRIFICE_CREATURE
                ));
                game.doAction(new DrawAction(it,2));
            })
        }
    }
]
