def ProtectionFromArtifacts = MagicAbility.getAbilityList("protection from artifacts");

def action = {
    final MagicGame game, final MagicEvent event ->
    game.doAction(new GainAbilityAction(
        event.getRefPermanent(),
        event.getChosenColor().getProtectionAbility()
    ));
}

[
    new MagicSpellCardEvent() {
        @Override
        public MagicEvent getEvent(final MagicCardOnStack cardOnStack, final MagicPayedCost payedCost) {
            return new MagicEvent(
                cardOnStack,
                new MagicOrChoice(
                    TARGET_PERMANENT_YOU_CONTROL,
                    TARGET_PERMANENT_YOU_CONTROL
                ),
                this,
                "Choose one\$ — (1) target permanent PN controls gains protection from artifacts until end of turn; " +
                "or (2) target permanent PN controls gains protection from the color of your choice until end of turn.\$"
            );
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            if (event.isMode(1)) {
                event.processTargetPermanent(game, {
                    game.doAction(new GainAbilityAction(it,ProtectionFromArtifacts))
                });
            }
            if (event.isMode(2)) {
                event.processTargetPermanent(game, {
                    game.addEvent(new MagicEvent(
                        event.getSource(),
                        event.getPlayer(),
                        MagicColorChoice.ALL_INSTANCE,
                        it,
                        action,
                        "Chosen color\$."
                    ));
                });
            }
        }
    }
]
