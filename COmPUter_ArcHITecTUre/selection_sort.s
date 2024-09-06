.data
arr: .word 64, 25, 12, 22, 11, 3, 12, 55, 4, 28


.text
addi x13, x0, 10    # n = 10
addi x14, x0, 0     # i = 0
addi x15, x0, 0     # j = 0
addi x16, x0, 0     # temp
la x12, arr         # Load starting address of arr into x12

start_loop:
bge x14,x20,exit_loop #if i>=n-1 goto exit_loop
add x17,x0,x14# min_idx=i

addi x15, x14, 1 #j = i+1

loop_j:
bge x13, x15,if_out# if n<j goto if

slli x20, x15, 2 # x20 = j*4 left shifting by 2 is multiplying by 4
add x21,x12, x20  # x21 = starting address of arr + j*4
lw x22, 0(x21) #x22=arr[j]

slli x23, x17, 2 # x20 =min_idx*4 left shifting by 2 is multiplying by 4
add x24,x12, x23  # x21 = starting address of arr +min_idx*4
lw x25, 0(x24) #x25=arr[min_idx]

blt x25, x22, if_out # jump if arr[min_idx]<arr[j] goto if_out
add x17,x15,x0 #min_idx=j

addi x15,x15,1 #j++
addi x14,x14,1 # i++
j start_loop

if_out:
bne x17,x14,exit_loop # if min_idx != i go out!
lw x16, 0(x25) #temp = arr[min_idx]
sw x22, 0(x25)#arr[min_idx] = arr[i]
lw x22, 0(x16)#arr[i] = temp

addi x14,x14,1 # i++
j start_loop

exit_loop:





loop_j:
    bge x15, x13, forchange # if j >= n, forchange

    slli x18, x15, 2    # x18 = j * 4
    add x19, x12, x18   # x19 = address of arr[j]
    lw x20, 0(x19)      # x20 = arr[j]

    slli x21, x17, 2    # x21 = min_idx * 4
    add x22, x12, x21   # x22 = address of arr[min_idx]
    lw x23, 0(x22)      # x23 = arr[min_idx]

    blt x20, x23, update_min # if arr[j] < arr[min_idx], update min_idx
    j jcontinue         # else continue

update_min:
    add x17, x15, x0    # min_idx = j

jcontinue:
    addi x15, x15, 1    # j++
    j loop_j            # repeat inner loop

forchange:
    bne x17, x14, change  # if min_idx != i, change

    addi x14, x14, 1    # i++
    j start_loop        # repeat outer loop

change:
    slli x24, x14, 2    # x24 = i * 4
    add x25, x12, x24   # x25 = address of arr[i]
    lw x26, 0(x25)      # x26 = arr[i]

    slli x27, x17, 2    # x27 = min_idx * 4
    add x28, x12, x27   # x28 = address of arr[min_idx]
    lw x29, 0(x28)      # x29 = arr[min_idx]

    sw x29, 0(x25)      # arr[i] = arr[min_idx]
    sw x26, 0(x28)      # arr[min_idx] = temp

    addi x14, x14, 1    # i++
    j start_loop        # repeat outer loop*/