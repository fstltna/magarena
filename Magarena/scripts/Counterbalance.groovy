[
    new OtherSpellIsCastTrigger() {
        @Override
        public MagicEvent executeTrigger(final MagicGame game,final MagicPermanent permanent,final MagicCardOnStack cardOnStack) {
            return permanent.isEnemy(cardOnStack) ?
                new MagicEvent(
                    permanent,
                    new MagicSimpleMayChoice(),
                    cardOnStack,
                    this,
                    "PN may\$ reveal the top card of his or her library. " +
                    "If PN does, counter RN if it has the same converted mana cost as the revealed card."
                ):
                MagicEvent.NONE;
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            if (event.isYes()) {
                for (final MagicCard card : event.getPlayer().getLibrary().getCardsFromTop(1)) {
                    game.doAction(new RevealAction(card));
                    if (card.getConvertedCost() == event.getRefCardOnStack().getConvertedCost()) {
                        game.doAction(new CounterItemOnStackAction(event.getRefCardOnStack()));
                    }
                }
            }
        }
    }
]
