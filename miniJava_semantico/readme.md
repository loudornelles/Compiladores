- Henrique Xavier
- Louise Dornelles
- Lucas Antunes

```bash
# build
sh build_all.sh

# run
sh run.sh ../tests/BubbleSort.java


sh build_all.sh
cd out
java Parser ../tests/Err1.java
```

## Validations to test for

- Unique method names [DONE]
- Access field of class [DONE][TESTED]
- No field access of other classes [DONE]
- Method call of current class [DONE]
  - Declared before callee [DONE]
  - Declared after callee [DONE]
- Method call of different class [DONE]
  - Class declared before current class [DONE]
  - Class declared after current class [DONE]
- Field of class type [DONE]
  - Class declared before current class [DONE]
  - Class declared after current class [DONE]
- No local variable with same name as parameter [DONE]


QUIQUI

- No two parameters with same name [DONE]
- No two local variables with same name [DONE]
- No two fields with the same name [DONE]
- Local variable shadowing field [?]
- Parameter shadowing field
- New operator
  - Class declared before current
  - Class declared after current
  - Return type
- Method call return type [DONE]
- Method call arguments [DONE]
  - Correct type [DONE]
  - Correct order [DONE]
- Operator operand types [DONE][TESTED]

LOW EASY [DONE]
- If, while only accept booleans [DONE]
- Assignment [DONE]
  - Correct type [DONE]
  - Incorrect type [DONE]
- Array assignment [DONE]
  - Correct type [DONE]
  - Incorrect type [DONE]
  - Correct index type [DONE][TESTED]
  - Incorrect index type [DONE]
- Length expression [DONE]
  - On correct type (arrays) [DONE]
  - On incorrect type [DONE]
  - Return type [DONE]