.data
dash: .string "- "
newline: .string "\n"
single_dash: .string "-"

.text
.globl main
# int main() {
#     ruler_draw(4);
#     return 0;
# }

main:
    li a0, 4 # Call ruler_draw(4)
    jal ruler_draw

    # Exit program
    li a0, 10
    ecall
    
#     void ruler_draw(int n) {
#     int i;
#     if (n == 1) {
#         printf("-");
#         printf("\n");
#         return;
#     }
#     ruler_draw(n - 1);
#     for (i=0; i<n; i++) {
#         printf("- ");
#     }
#     printf("\n");
#     ruler_draw(n - 1);
# }

ruler_draw:
    addi sp, sp, -16
    sw ra, 12(sp)  # save or push ra on to the stack
    sw s0, 8(sp) # save or push s0 on to the stack
    sw s1, 4(sp)# save or push s1 on to the stack
    sw s2, 0(sp)# save or push s2 on to the stack

    mv s0, a0  # s0 = n
    mv x19, s0  # x19 = n (as per register allocation)

    # if (n == 1)
    li t0, 1
    bne s0, t0, recursive_case #if n!=1 goto recursive case

    # Base case: n == 1
    # Print single dash
    li a0, 4
    la a1, single_dash
    ecall

    # Print newline
    li a0, 4
    la a1, newline
    ecall

    j end_rulerdraw

recursive_case:
    # ruler_draw(n - 1)
    addi a0, s0, -1 #pass a0=n-1 goto ruler_draw
    jal ruler_draw 

    # for (i=0; i<n; i++)
    li x18, 0  # i = 0
    mv s1, x18  # s1 = i

print_loop:
    bge s1, s0, end_loop #if i>=n goto end_loop

    # Print dash "- "
    li a0, 4
    la a1, dash
    ecall

    addi s1, s1, 1 #i++
    j print_loop

end_loop:
    # Print newline
    li a0, 4
    la a1, newline
    ecall

    addi a0, s0, -1# ruler_draw(n - 1) goto recursive function ruler_draw
    jal ruler_draw

end_rulerdraw:
    lw s2, 0(sp) # คืนค่า s2
    lw s1, 4(sp) # คืนค่า s1
    lw s0, 8(sp)# คืนค่า s0
    lw ra, 12(sp)# คืนค่า ra
    addi sp, sp, 16  # คืนพื้นที่ stack
    ret


