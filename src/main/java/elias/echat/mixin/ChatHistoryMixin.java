package elias.echat.mixin;

import elias.echat.CommandsClass;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.gui.hud.ChatHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ChatHud.class)
public class ChatHistoryMixin {

	// Modify the chat history limit dynamically based on the value set by the command
	@ModifyExpressionValue(
			method = {"addMessage", "addVisibleMessage", "addMessage"},
			at = @At(value = "CONSTANT", args = "intValue=100")
	)
	private int modifyChatHistoryLimit(int original) {
		// Get the limit from the EchatCommand class
		return CommandsClass.getChatHistoryLimit();
	}
}





