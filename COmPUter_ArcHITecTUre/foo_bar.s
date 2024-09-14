# int main() {
# 	int result;
# 	result = foo(3, 5);
# printf("result is %d\n", result);
.data
result_is:.string "result is "
new_line: .string "\n"

.text
.globl main
main:
#book area of stack for pass argument
addi sp,sp,-8 #allocate 8 bytes on the stack การปรับค่าลง 8 ไบก์ //จองพื้นที่
addi x19,x0,3 #temp1=3
addi x20,x0,5 #temp2=5
sw x20,0(sp)# save or push temp2 (x20) on to the stack ส่งจากหลังมาหน้า 5-3
sw x19,0(sp)# save or push temp1 (x19) on to the stack

#jump to function
jal foo #jump and link to foo // or call foo function

#result = foo(3, 5);
add x18,x0,a0 

#คืนพื้นที่ ที่เคยจองไปจองเท่าไหร่ คืนเท่านั้น
addi sp,sp,8 #deallocate 8 bytes on the stack 

#printf result is
addi a0, x0, 4  # put code 4 to a0
la a1, result_is  # put address of result_is  to a1
ecall

#print value of result 
addi a0, x0, 1  # put code 1 to a0 
add a1, x0, x18 # put the value of result  in a1 
ecall

#print newline
addi a0, x0, 4  # put code 4 to a0
la a1, new_line # put address of new_line to in a1
ecall

#return 0;
addi a0, x0, 10 # put code 10 in a0
add a1, x0, x0 # set a1 to zero
ecall

bar:
# int bar(int n) {
#     int i, sum = 0;
#     for (i=0; i<n; i++)
#         sum += i + 1;
#     return sum;
# }
# n= x18 i= x19 sum= x20
addi x19,x0,0 #i=0
addi x20,x0,0 #sum = 0
lw x18,0(sp)  #get n from the stack

start_bar_forloop:
bge x19,x18,exit_loop #if i>=n go to exit_loop
add x20,x20,x19 #sum = sum+i
addi x20,x20,1 #sum=sum+1

addi x19,x19,1 #i++
j start_bar_forloop

foo:
# int foo(int x, int n) {
# 	int i, sum;
# 	sum = 0;
# 	for (i=0; i<n; i++) {
# 		sum += x;
# 	}
#     	sum = sum + bar(10);
# 	return sum;
# }
addi x20,x0,0 #i=0
addi x21,x0,0 #sum=0
lw x18,0(sp)  #get x from the stack ค่าของ 3 อยู่ที่stack address 0
lw x19,4(sp)  #get n from the stack  ค่าของ 5 อยู่ที่stack address 4

#for (i=0; i<n; i++)
start_foo_forloop:
bge x20,x19,exit_loop_2 #if i >= n go to exit_loop
add x21,x21,x18 #sum+=x

addi x20,x20,1 #i++
j start_foo_forloop

exit_loop:
add a0,x0,x20 #return sum at regis x20
jr ra

exit_loop_2:
# save ค่า register ของตัวแปร sum,ra 
addi sp,sp,-8 #allocate 8 bytes on the stack 
sw x21,4(sp) # save or push sum on to the stack
sw ra,0(sp)# save or push ra on to the stack

#pass argument to bar
addi sp,sp,-4 #allocate 4 bytes on the stack 
addi x22,x0,10 #x22=10
sw x22,0(sp)  # save or push ra on to the stack

#jump to function
jal bar #jump and link to bar // or bar foo function

addi sp,sp,4
lw x21,4(sp)

#sum = sum + bar(10);
add x21,x21,a0 

#place sum to a0
add a0,x21,x0 


lw ra,0(sp) #เอาค่า ra คืน
#และคืนstack ทั้งหมด
addi sp,sp,8

#return sum
jr ra


