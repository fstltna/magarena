[
    new IfLifeWouldChangeTrigger() {
        @Override
        public MagicEvent executeTrigger(final MagicGame game,final MagicPermanent permanent,final ChangeLifeAction act) {
            if (act.getLifeChange() > 0) {
                act.setLifeChange(0);
            }
            return MagicEvent.NONE;
        }
    }
]
