addi x12, x0, 0 #a
addi x13, x0, 1 #b
addi x14, x0, 8 #n
bne x14, x0, else_if #if n != 0 goto else_if
add x15, x0, x12 #fib_n=a
j exit
else_if : 
addi x20,x0,1 #Constant1
bne x14, x20, else #if n != 1 goto else
add  x15, x0, x13 #fib_n=b
j exit
else: 
addi x16, x0, 2 #i=2
start_loop:
bgt x16, x14,exit_loop #if i>n goto exit_loop
add x17, x12, x13 #c=a+b
add x12, x13, x0 #a=b
add x13, x17, x0 #b=c
addi x16,x16,1 #i++
j start_loop 
exit_loop: 
add x15, x13, x0 
exit: 
add x17, x0, x0
