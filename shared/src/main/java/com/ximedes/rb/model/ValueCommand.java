package com.ximedes.rb.model;

import lombok.EqualsAndHashCode;
import lombok.Value;

/**
 * A command, in case no extra parameters are needed
 */
@Value
@EqualsAndHashCode(callSuper = true)
public class ValueCommand extends Command {
    String value;
}
