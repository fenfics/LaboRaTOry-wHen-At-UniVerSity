.data
source: .word 3, 1, 4, 1, 5, 9, 0
dest: .word 0, 0, 0, 0, 0, 0, 0, 0, 0, 0

.text
la x12, source # load starting address of source to x12
la x13, dest # load starting address of dest to x13
addi x14, x0, 0 #k=0
#exit loop when source[k] equals zero
start_loop:
slli x20, x14, 2 # x20 = k*4 left shifting by 2 is multiplying by 4
add x21, x12, x20 # x21 = starting address of source + k*4

lw x22, 0(x21) # x22 = source[k]

beq x22, x0, exit_loop
add x23, x13, x20 #x23 = starting address of dest + k*4
sw x22, 0(x23) #dest[k] = source[k]
addi x14, x14, 1 #k++
j start_loop

exit_loop:
