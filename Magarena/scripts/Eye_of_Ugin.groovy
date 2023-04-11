[
    new MagicStatic(MagicLayer.CostReduction) {
        @Override
        public MagicManaCost reduceCost(final MagicPermanent source, final MagicCard card, final MagicManaCost cost) {
            if (MagicColor.isColorless(card) && card.hasSubType(MagicSubType.Eldrazi) && source.isFriend(card)) {
                return cost.reduce(2);
            } else {
                return cost;
            }
        }
    }
]
