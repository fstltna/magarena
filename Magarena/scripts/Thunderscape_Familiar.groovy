[
    new MagicStatic(MagicLayer.CostReduction) {
        @Override
        public MagicManaCost reduceCost(final MagicPermanent source, final MagicCard card, final MagicManaCost cost) {
            if ((card.hasColor(MagicColor.Black) || card.hasColor(MagicColor.Green)) && source.isFriend(card)) {
                return cost.reduce(1);
            } else {
                return cost;
            }
        }
    }
]
