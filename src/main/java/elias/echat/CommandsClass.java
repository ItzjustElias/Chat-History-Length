package elias.echat;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class CommandsClass {
    // Default chat history limit
    private static int chatHistoryLimit = 100;

    // Minimum limit that users can't go below
    private static final int MIN_CHAT_HISTORY_LIMIT = 100;

    // Max limit, can be configured
    private static final int MAX_CHAT_HISTORY_LIMIT = 16384;

    // Getter for the chat history limit, used in the mixin
    public static int getChatHistoryLimit() {
        return chatHistoryLimit;
    }

    // Register the command /chathistory to adjust chat history limit
    public static void registerCommand(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("chathistory")
                .then(CommandManager.argument("limit", IntegerArgumentType.integer(MIN_CHAT_HISTORY_LIMIT, MAX_CHAT_HISTORY_LIMIT))
                        .executes(context -> {
                            int limit = IntegerArgumentType.getInteger(context, "limit");

                            // Set the new chat history limit
                            chatHistoryLimit = limit;

                            // Inform the player of the new limit
                            ServerCommandSource source = context.getSource();
                            if (source.getEntity() instanceof ServerPlayerEntity) {
                                ServerPlayerEntity player = (ServerPlayerEntity) source.getEntity();
                                // Sending a system message to the player
                                player.sendMessage(Text.literal("Chat history limit set to " + chatHistoryLimit));
                            }

                            return 1; // Success
                        })
                )
                .executes(context -> {
                    // Error message if limit is not provided
                    context.getSource().sendError(Text.literal("Usage: /chathistory <limit>"));
                    return 0;
                })
        );
    }
}
