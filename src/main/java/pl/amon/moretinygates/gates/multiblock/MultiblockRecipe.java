package pl.amon.moretinygates.gates.multiblock;

import java.util.HashMap;

public class MultiblockRecipe {
  private String[] inputs;
  private String[] outputs;
  private HashMap<Character, String> types;

  public String[] getInputs() {
    return inputs;
  }

  public String[] getOutputs() {
    return outputs;
  }
}

/**
 * 
 * 
 * 
 {
  inputs: ["0", "1", "2"],
  outputs: ["0"],
  types: {
    a: "pl.amon.moretinygates:silicon_module",
    b: "core",
    c: "pl.amon.moretinygates:silicon_module"
  },
  constructions: [
    {
      plan: ["abc"],
      outputs: {
        "0": "a:top",
        "1": "b:top",
        "2": "c:top"
      },
      inputs: {
        "0": "b:bottom"
      }
    },
    {
      plan: [
        "a",
        "b",
        "c",
      ],
      outputs: {
        "0": "a:right",
        "1": "b:right",
        "2": "c:right"
      },
      inputs: {
        "0": "b:left"
      }
    }, 
  ]
}
 * 
 * 
 */