# int main() {
#     int my_num = 4;
#     int my_fib;
#     my_fib = fib_iter(12);
#     my_num++;
#     printf("my_fib = %d\n", my_fib);
#     printf("my_num = %d\n", my_num);
#     return 0;
# }
.data
my_fib: .string"my_fib ="
my_num: .string "my_num ="
new_line: .string "\n"

.text
.globl main
main:
addi x18,x0,4 #my_num = 4
addi sp,sp,-4 #allocate 4 bytes on the stack การปรับค่าลง 4 ไบก์
sw x18,0(sp) #save my_num (x18) on to the stack

#book area of stack for pass argument
addi sp,sp,-4 #allocate 4 bytes on the stack การปรับค่าลง 4 ไบก์ //จองพื้นที่
addi x20,x0,12 #temp=12
sw x20,0(sp)# save or push temp (x20) on to the stack

#jump to function
jal fib_iter #jump and link to fib_iter // or call fib_iter function

#my_fib = fib_iter(12)
add x19,x0,a0 #my_fib = fib_iter(12); waiting return value(a0) from fib_iter(12) 

#คืนพื้นที่ ที่เคยจองไปจองเท่าไหร่ คืนเท่านั้น
addi sp,sp,4 #deallocate 4 bytes on the stack 
lw x18,0(sp) #restored my_num โหลดค่ากลับมาที่ register ตัวเดิมx18
addi x18,x18,1#my_num++

#คืนพื้นที่ ที่เคยจองไปจองเท่าไหร่ คืนเท่านั้น
addi sp,sp,4 #deallocate 4 bytes on the stack 

#printf my_fib = 
addi a0, x0, 4  # put code 4 to a0
la a1, my_fib  # put address of my_fib  to a1
ecall

#print value of my_fib
addi a0, x0, 1  # put code 1 to a0 ตัวที่จะปริ้นไว้ที่ a0
add a1, x0, x19 # put the value of x in a1 a1 เก็บค่าตัวที่จะปริ้น
ecall


#print newline
addi a0, x0, 4  # put code 4 to a0
la a1, new_line # put address of new_line to in a1
ecall



#printf my_num =
addi a0, x0, 4  # put code 4 to a0
la a1, my_num # put address of my_num to a1
ecall


#print value of my_num
addi a0, x0, 1  # put code 1 to a0 ตัวที่จะปริ้นไว้ที่ a0
add a1, x0, x18 # put the value of my_num
ecall

#print newline
addi a0, x0, 4  # put code 4 to a0
la a1, new_line # put address of new_line to in a1
ecall


#return 0;
addi a0, x0, 10 # put code 10 in a0
add a1, x0, x0 # set a1 to zero
ecall

# int fib_iter(int n) {
#     int curr_fib = 0, next_fib = 1;
#     int new_fib;
#     for (int i = n; i > 0; i--) {
#         new_fib = curr_fib + next_fib;
#         curr_fib = next_fib;
#         next_fib = new_fib;
#     }
#     return curr_fib;
# }
fib_iter:
addi x19,x0,0 # curr_fib = 0
addi x20,x0,1 #next_fib = 1
lw x18,0(sp)  #get n from the stack
add x22,x18,x0 #i = n

start_forloop:
#for (int i = n; i > 0; i--)
ble x22,x0,exit_loop #if i<= 0 go to exit_loop
add x21,x19,x20 # new_fib = curr_fib + next_fib;
add x19,x20,x0 #curr_fib = next_fib;
add x20,x21,x0#next_fib = new_fib;
addi x22,x22,-1 #i--
j start_forloop

exit_loop:
# return curr_fib;
addi a0,x19,0 #move x19 value to a0
jr ra #go to add x19,x0,a0 #my_fib = fib_iter(12);