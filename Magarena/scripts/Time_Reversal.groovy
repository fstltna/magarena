[
    new MagicSpellCardEvent() {
        @Override
        public MagicEvent getEvent(final MagicCardOnStack cardOnStack,final MagicPayedCost payedCost) {
            return new MagicEvent(
                cardOnStack,
                this,
                "Each player shuffles his or her hand and graveyard into his or her library, then draws seven cards. "+
                "Exile SN."
            );
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            for (final MagicPlayer player : game.getAPNAP()) {
                final MagicCardList graveyard = new MagicCardList(player.getGraveyard());
                for (final MagicCard card : graveyard) {
                    game.doAction(new ShiftCardAction(
                        card,
                        MagicLocationType.Graveyard,
                        MagicLocationType.TopOfOwnersLibrary
                    ));
                }
                final MagicCardList hand = new MagicCardList(player.getHand());
                for (final MagicCard card : hand) {
                    game.doAction(new ShiftCardAction(
                        card,
                        MagicLocationType.OwnersHand,
                        MagicLocationType.TopOfOwnersLibrary
                    ));
                }
                game.doAction(new ShuffleLibraryAction(player));
            }
            for (final MagicPlayer player : game.getAPNAP()) {
                game.doAction(new DrawAction(player,7));
            }
            game.doAction(new ChangeCardDestinationAction(event.getCardOnStack(),MagicLocationType.Exile));
        }
    }
]
