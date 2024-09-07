.data
arr: .word 64, 25, 12, 22, 11, 3, 12, 55, 4, 28  # Array to sort

.text
    addi x13, x0, 10      # n = 10
    la x12, arr           # Load starting address of arr into x12
    addi x14, x0, 0       # i = 0

outer_loop:
    addi x11, x13, -1     # x11 = n - 1
    bge x14, x11, exit    # if i >= n - 1, exit loop
    add x17, x14, x0      # min_idx = i
    addi x15, x14, 1      # j = i + 1

inner_loop:
    bge x15, x13, swap    # if j >= n, go to swap
    slli x20, x15, 2      # x20 = j * 4
    add x21, x12, x20     # x21 = address of arr[j]
    lw x22, 0(x21)        # x22 = arr[j]
    slli x23, x17, 2      # x23 = min_idx * 4
    add x24, x12, x23     # x24 = address of arr[min_idx]
    lw x25, 0(x24)        # x25 = arr[min_idx]
    bge x22, x25, skip_update # if arr[j] >= arr[min_idx], skip update
    add x17, x15, x0      # min_idx = j

skip_update:
    addi x15, x15, 1      # j++
    j inner_loop          # continue inner_loop

swap:
    beq x17, x14, skip_swap # if min_idx == i, skip swap
    slli x26, x14, 2      # x26 = i * 4
    add x27, x12, x26     # x27 = address of arr[i]
    lw x28, 0(x27)        # x28 = arr[i]
    slli x23, x17, 2      # x23 = min_idx * 4
    add x24, x12, x23     # x24 = address of arr[min_idx]
    lw x16, 0(x24)        # temp = arr[min_idx]
    sw x28, 0(x24)        # arr[min_idx] = arr[i]
    sw x16, 0(x27)        # arr[i] = temp

skip_swap:
    addi x14, x14, 1      # i++
    j outer_loop          # continue outer_loop

exit:
    # End of program
