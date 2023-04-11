[
    new OtherSpellIsCastTrigger() {
        @Override
        public MagicEvent executeTrigger(final MagicGame game,final MagicPermanent permanent,final MagicCardOnStack cardOnStack) {
            return new MagicEvent(
                permanent,
                cardOnStack.getController(),
                cardOnStack,
                this,
                "SN deals X damage to PN, where X is the number of cards in all graveyards with the same name as RN."
            );
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            final String name = event.getRefCardOnStack().getCard().getName();
            final int amount = cardName(name)
                .from(MagicTargetType.Graveyard)
                .from(MagicTargetType.OpponentsGraveyard)
                .filter(event)
                .size();
            game.logAppendX(event.getPlayer(),amount)
            game.doAction(new DealDamageAction(event.getSource(),event.getPlayer(),amount));
        }
    }
]
