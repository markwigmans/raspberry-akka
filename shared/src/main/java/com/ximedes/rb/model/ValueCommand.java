package com.ximedes.rb.model;

import lombok.Value;

/**
 * A command, in case no extra parameters are needed
 */
@Value
public class ValueCommand extends Command {
    String value;
}
