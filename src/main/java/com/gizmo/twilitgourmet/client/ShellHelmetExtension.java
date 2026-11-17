package com.gizmo.twilitgourmet.client;

import twilightforest.client.renderer.armor.TFSimpleArmorRenderer;

public class ShellHelmetExtension extends TFSimpleArmorRenderer {
    public ShellHelmetExtension() {
        super(ShellHelmetModel::new, ShellHelmetModel.SHELL_HELMET_INNER, ShellHelmetModel.SHELL_HELMET_OUTER);
    }
}
