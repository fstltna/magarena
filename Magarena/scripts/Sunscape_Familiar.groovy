[
    new MagicStatic(MagicLayer.CostReduction) {
        @Override
        public MagicManaCost reduceCost(final MagicPermanent source, final MagicCard card, final MagicManaCost cost) {
            if ((card.hasColor(MagicColor.Green) || card.hasColor(MagicColor.Blue)) && source.isFriend(card)) {
                return cost.reduce(1);
            } else {
                return cost;
            }
        }
    }
]
