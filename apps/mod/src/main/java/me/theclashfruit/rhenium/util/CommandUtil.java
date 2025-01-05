package me.theclashfruit.rhenium.util;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandUtil {
    public static Map<String, Object> parseCommand(String command) {
        Pattern pattern = Pattern.compile("(\\w+)\\((.*)\\)");
        Matcher matcher = pattern.matcher(command);

        Map<String, Object> commandMap = new HashMap<>();

        if (matcher.find()) {
            commandMap.put("function", matcher.group(1));

            String args = matcher.group(2).trim();
            if (!args.isEmpty()) {
                List<String> arguments = Arrays.stream(args.split("\\s*,\\s*"))
                    .map(arg -> arg.replaceAll("^['\"]|['\"]$", ""))
                    .toList();

                commandMap.put("arguments", arguments);
            }
        }

        return commandMap;
    }
}
