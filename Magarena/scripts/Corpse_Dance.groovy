[
    new MagicSpellCardEvent() {
        @Override
        public MagicEvent getEvent(final MagicCardOnStack cardOnStack,final MagicPayedCost payedCost) {
            return new MagicEvent(
                cardOnStack,
                this,
                "PN returns the top creature card of his or her graveyard\$ to the battlefield. " +
                "That creature gains haste until end of turn. " +
                "Exile it at the beginning of the next end step. " +
                "If the buyback cost was payed, return SN to its owner's hand as it resolves."
            );
        }

        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            final MagicPlayer player = event.getPlayer();
            final List<MagicCard> targets = CREATURE_CARD_FROM_GRAVEYARD.filter(event);
            if (targets.size() > 0) {
                final MagicCard card = targets.get(targets.size()-1);
                game.doAction(new ReanimateAction(
                    card,
                    player,
                    [MagicPlayMod.HASTE_UEOT, MagicPlayMod.EXILE_AT_END_OF_TURN]
                ));
            }
            if (event.isBuyback()) {
                game.doAction(new ChangeCardDestinationAction(event.getCardOnStack(), MagicLocationType.OwnersHand));
            }
        }
    }
]
