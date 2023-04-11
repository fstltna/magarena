[
    new MagicSpellCardEvent() {
        @Override
        public MagicEvent getEvent(final MagicCardOnStack cardOnStack,final MagicPayedCost payedCost) {
            return new MagicEvent(
                cardOnStack,
                TARGET_OPPONENT,
                this,
                "SN deals X damage to target opponent\$, where X is twice the number of white creatures that player controls."
            );
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            event.processTargetPlayer(game, {
                final int amount = it.getNrOfPermanents(WHITE_CREATURE) * 2;
                game.logAppendX(event.getPlayer(),amount);
                if (amount>0) {
                    game.doAction(new DealDamageAction(event.getSource(),it,amount));
                }
            });
        }
    }
]
