[
    new MagicSpellCardEvent() {
        @Override
        public MagicEvent getEvent(final MagicCardOnStack cardOnStack, final MagicPayedCost payedCost) {
            return new MagicEvent(
                cardOnStack,
                NEG_TARGET_PLAYER,
                new MagicDamageTargetPicker(4),
                this,
                "SN deals 4 damage to target player\$ and 1 damage to each creature that player controls."
            );
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            event.processTargetPlayer(game, {
                game.doAction(new DealDamageAction(event.getSource(), it, 4));
                CREATURE_YOU_CONTROL.filter(it) each {
                    final MagicPermanent target ->
                    game.doAction(new DealDamageAction(event.getSource(), target, 1));
                }
            });
        }
    }
]
