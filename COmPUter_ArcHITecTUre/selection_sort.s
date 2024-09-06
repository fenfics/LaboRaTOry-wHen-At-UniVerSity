.data
arr: .word 64, 25, 12, 22, 11, 3, 12, 55, 4, 28


.text
    la x12, arr           # Load starting address of arr into x12
    addi x13, x0, 10      # n = 10
    addi x14, x0, 0       # i = 0
    addi x16, x0, 0       # temp = 0

start_loop:
    addi x20, x13, -1     # x20 = n - 1
    bge x14, x20, exit_loop # if i >= n-1, goto exit_loop
    add x17, x0, x14      # min_idx = i

    addi x15, x14, 1      # j = i + 1

loop_j:
    bge x15, x13, forchange # if j >= n, goto forchange

    slli x20, x15, 2      # x20 = j * 4 (left shift by 2)
    add x21, x12, x20     # x21 = address of arr[j]
    lw x22, 0(x21)        # x22 = arr[j]

    slli x23, x17, 2      # x23 = min_idx * 4 (left shift by 2)
    add x24, x12, x23     # x24 = address of arr[min_idx]
    lw x25, 0(x24)        # x25 = arr[min_idx]

    blt x22, x25, update_min # if arr[j] < arr[min_idx], jump to update_min

continue_j:
    addi x15, x15, 1      # j++
    j loop_j              # repeat inner loop

update_min:
    add x17, x15, x0      # min_idx = j
    j continue_j          # continue inner loop

forchange:
    bne x17, x14, change # if min_idx != i, skip swap
    
    addi x14, x14, 1      # i++
    j start_loop          # repeat outer loop


change: # Perform the swap
    slli x20, x14, 2      # x20 = i * 4
    add x21, x12, x20     # x21 = address of arr[i]
    lw x16, 0(x21)        # temp = arr[i]

    slli x23, x17, 2      # x23 = min_idx * 4
    add x24, x12, x23     # x24 = address of arr[min_idx]
    lw x25, 0(x24)        # x25 = arr[min_idx]

    sw x25, 0(x21)        # arr[i] = arr[min_idx]
    sw x16, 0(x24)        # arr[min_idx] = temp

exit_loop:
