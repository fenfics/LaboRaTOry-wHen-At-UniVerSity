.data
arr: .word 64,25,12,22,11,3,12,55,4,28 #arr[] x12

.text #keepคำสั่ง
addi x13, x0 ,10 #n=10
la x12, arr # load starting address of arr to x12(&arr[0]=x12) 
lw x15, 0(x12) # min_val = arr[0]
addi x14, x0, 0 #i=0

start_loop:
    bge x14, x13, exit_loop   # if i >= n, exit loop

    slli x20, x14, 2 # x20 = i*4 left shifting by 2 is multiplying by 4
    add x21,x12, x20  # x21 = starting address of arr + i*4

    lw x22, 0(x21) #x22=arr[i]
    
    
    blt x22, x15, update_min  # if arr[i] < min_val, update min_val
    

    addi x14, x14, 1 #i++
    j start_loop

update_min:
    lw x15, 0(x21) #min_val=arr[i]
    addi x14, x14, 1 #i++
    j start_loop
    
exit_loop:
