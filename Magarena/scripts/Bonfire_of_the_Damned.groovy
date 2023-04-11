[
    new MagicSpellCardEvent() {
        @Override
        public MagicEvent getEvent(final MagicCardOnStack cardOnStack, final MagicPayedCost payedCost) {
            final int amount = payedCost.getX();
            return new MagicEvent(
                cardOnStack,
                NEG_TARGET_PLAYER,
                new MagicDamageTargetPicker(amount),
                this,
                "SN deals " + amount +
                " damage to target player\$ and each creature he or she controls."
            );
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            event.processTargetPlayer(game, {
                final int amount = event.getCardOnStack().getX();
                game.doAction(new DealDamageAction(event.getSource(), it, amount));
                CREATURE_YOU_CONTROL.filter(it) each {
                    game.doAction(new DealDamageAction(event.getSource(), it, amount));
                }
            });
        }
    }
]
