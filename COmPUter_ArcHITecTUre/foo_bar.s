.data
result_is: .string "result is "
new_line: .string "\n"

.text
.globl main

main:
    addi sp, sp, -8     # จองพื้นที่บน stack 8 ไบต์
    li a0, 3            # เตรียมอาร์กิวเมนต์แรก (x = 3)
    li a1, 5            # เตรียมอาร์กิวเมนต์ที่สอง (n = 5)
    sw a0, 0(sp)        # เก็บ x บน stack
    sw a1, 4(sp)        # เก็บ n บน stack
    jal foo             # เรียกฟังก์ชัน foo
    mv s0, a0           # เก็บผลลัพธ์ใน s0

    # พิมพ์ "result is "
    li a0, 4
    la a1, result_is
    ecall

    # พิมพ์ค่าผลลัพธ์
    li a0, 1
    mv a1, s0
    ecall

    # พิมพ์บรรทัดใหม่
    li a0, 4
    la a1, new_line
    ecall

    # จบโปรแกรม
    li a0, 10
    ecall

bar:
    mv t0, a0           # n = a0
    li t1, 0            # i = 0
    li t2, 0            # sum = 0
bar_loop:
    bge t1, t0, bar_end # ถ้า i >= n, ออกจากลูป
    addi t3, t1, 1      # t3 = i + 1
    add t2, t2, t3      # sum += (i + 1)
    addi t1, t1, 1      # i++
    j bar_loop
bar_end:
    mv a0, t2           # ส่งคืนค่า sum
    ret

foo:
    addi sp, sp, -16    # จองพื้นที่บน stack
    sw ra, 12(sp)       # บันทึก ra
    sw s0, 8(sp)        # บันทึก s0
    sw s1, 4(sp)        # บันทึก s1
    sw s2, 0(sp)        # บันทึก s2

    lw s0, 16(sp)       # โหลด x จาก stack
    lw s1, 20(sp)       # โหลด n จาก stack
    li s2, 0            # sum = 0

foo_loop:
    beqz s1, foo_end    # ถ้า n == 0, ออกจากลูป
    add s2, s2, s0      # sum += x
    addi s1, s1, -1     # n--
    j foo_loop

foo_end:
    li a0, 10           # เตรียมอาร์กิวเมนต์สำหรับ bar(10)
    jal bar             # เรียก bar(10)
    add s2, s2, a0      # sum += bar(10)

    mv a0, s2           # เตรียมค่าสำหรับส่งคืน

    lw s2, 0(sp)        # คืนค่า s2
    lw s1, 4(sp)        # คืนค่า s1
    lw s0, 8(sp)        # คืนค่า s0
    lw ra, 12(sp)       # คืนค่า ra
    addi sp, sp, 16     # คืนพื้นที่ stack

    ret                 # กลับจากฟังก์ชัน
