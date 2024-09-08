#address เริ่มต้นของ sorted_arr -> x12
#left -> x13 unsigned
#right -> x14 unsigned
#mid -> x15 unsigned
#result -> x16 unsigned
#num_step -> x17 unsigned
#arr_size -> x18 unsigned
#key -> x19
#result_2 -> x20 unsigned
#num_step_2 -> x21 unsigned
#i -> x22 unsigned
.data
sorted_arr: .word -126, -115, -99, -75, -45, -23, -12, 0, 15, 38, 57, 78, 89, 103, 126

.text
.globl main
main:
    la x12, sorted_arr     # Load the address of sorted_arr into x12
    addi x18,x0, 15             # arr_size = 15
    addi x13,x0,0              # left = 0
    addi x14, x18, -1      # right = arr_size - 1
    addi x19,x0,89            # key = 89
    addi x17,x0,0           # num_step = 0


# binary_search    
    addi x16,x0,-1             # result = -1
while_loop:
    bgtu x13, x14, sequential_search # if left > right goto sequential_search
    addi x17, x17, 1       # num_step++
    sub x5, x14, x13       # x5 = right - left
    srli x5, x5, 1          # x5 = (right - left) >> 1
    add x15, x13, x5       # mid = left + ((right - left) >> 1)

    slli x5, x15, 2        # x5 = mid * 4
    add x5, x12, x5        # x5 = address of sorted_arr[mid]
    lw x5, 0(x5)           # load sorted_arr[mid] value into x5

    beq x5, x19, found     # if sorted_arr[mid] == key goto found
    blt x5, x19, less      # if sorted_arr[mid] < key goto less
    addi x14, x15, -1      # right = mid - 1
    j while_loop

less:
    addi x13, x15, 1       # left = mid + 1
    j while_loop

found:
    add x16,x0,x15            # result = mid
   

# sequential_search
sequential_search:
    li x21, 0              # num_step_2 = 0
    li x20, -1             # result_2 = -1
    li x22, 0              # i = 0

for_loop:
    bge x22, x18, exit_loop # if i >= arr_size goto exit_loop
    addi x21, x21, 1       # num_step_2++

    slli x5, x22, 2        # x5 = i * 4
    add x5, x12, x5        # x5 = address of sorted_arr[i]
    lw x5, 0(x5)           # load sorted_arr[i] value into x5

    beq x5, x19, found_sequential # if sorted_arr[i] == key goto found_sequential
    addi x22, x22, 1       # i++
    j for_loop

found_sequential:
    add x20, x22, x0    # result_2 = i
j exit_loop

exit_loop:
    li a0, 0               # Return 0
    ret                    # Return from main

