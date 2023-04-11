def TEXT1 = "Each player shuffles his or her hand and graveyard into his or her library."

def TEXT2 = "Each player draws seven cards."

[
    new MagicSpellCardEvent() {
        @Override
        public MagicEvent getEvent(final MagicCardOnStack cardOnStack, final MagicPayedCost payedCost) {
            return new MagicEvent(
                cardOnStack,
                payedCost.isKicked() ?
                    MagicChoice.NONE :
                    new MagicOrChoice(
                        MagicChoice.NONE,
                        MagicChoice.NONE
                    ),
                this,
                payedCost.isKicked() ?
                    TEXT1 + " " + TEXT2 :
                    "Choose one\$ — (1) " + TEXT1 + " (2) " + TEXT2
            );
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            if (event.isKicked() || event.isMode(1)) {
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
            }
            if (event.isKicked() || event.isMode(2)) {
                for (final MagicPlayer player : game.getAPNAP()) {
                    game.doAction(new DrawAction(player,7));
                }
            }
        }
    }
]
