.data
str1: .string "Factorial of "
str2: .string " is "
newline: .string "\n"

.text
.globl main

main:
    addi sp, sp, -4
    sw ra, 0(sp)

    li x18, 0  # i = 0
    li x19, 9  # x19=9

loop:
    bge x18, x19, exit_loop #ifi>=9 goto exit_loop

    # Call fact(i)
    mv a0, x18
    jal fact # Call fact(i)
    mv x20, a0  # Store result in x20

    # Print "Factorial of "
    li a0, 4
    la a1, str1
    ecall

    # Print i
    li a0, 1
    mv a1, x18
    ecall

    # Print " is "
    li a0, 4
    la a1, str2
    ecall

    # Print result
    li a0, 1
    mv a1, x20
    ecall

    # Print newline
    li a0, 4
    la a1, newline
    ecall
    
    addi x18, x18, 1#i++
    j loop
    

fact:
    addi sp, sp, -8
    sw ra, 4(sp) 
    sw x19, 0(sp)  # Save x19 (n) as it's callee-saved

    mv x19, a0  # n = argument

    # Base case: if (n == 0) return 1
    beqz x19, base_case

    # Recursive function: result = fact(n-1) * n
    addi a0, x19, -1
    jal fact
    mul x20, a0, x19  # result = fact(n-1) * n
    j end_fact

base_case:
    li x20, 1  # result = 1

end_fact:
    mv a0, x20  # Set return value

    # Epilogue
    lw x19, 0(sp)
    lw ra, 4(sp)
    addi sp, sp, 8
    ret



exit_loop:
    lw ra, 0(sp)
    addi sp, sp, 4

    # Return 0
    li a0, 0
    ret