//? if >=26.1 {
/*package mod.icanttellyou.picturemode.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import mod.icanttellyou.picturemode.client.PictureModeClient;
import mod.icanttellyou.picturemode.client.PictureModeState;
import net.minecraft.client.CloudStatus;
import net.minecraft.client.Options;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(
    //? if >=26.4 {
    //Options.class
    //? } else {
    net.minecraft.client.renderer.GameRenderer.class
    //? }
)
public class OptionsMixin {
    @WrapOperation(
        //~ if >=26.4 'extractOptions' -> 'extractRenderState'
        method = "extractOptions",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/Options;getCloudStatus()Lnet/minecraft/client/CloudStatus;"
        )
    )
    private CloudStatus hideCloudsInPM(Options instance, Operation<CloudStatus> original) {
        PictureModeState state = PictureModeClient.getState();

        if (state != null && state.isEnabled()) {
            return CloudStatus.OFF;
        }

        return original.call(instance);
    }
}
*///? }