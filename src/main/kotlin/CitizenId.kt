package org.example

fun validateCitizenId(id: String): Boolean {
    // 1. เช็คความยาว 13 ตัว และทุกตัวต้องเป็นตัวเลข (isDigit รองรับเลขไทย ๐-๙ ด้วย)
    if (id.length != 13 || !id.all { it.isDigit() }) {
        return false
    }

    // 2. digitToInt() แปลงตัวเลข Unicode ทุกภาษา (รวมทั้งเลขไทย) เป็นค่า 0-9 ได้ทันที
    val digits = id.map { it.digitToInt() }

    // 3. คำนวณ Checksum Modulo 11
    val sum = digits.take(12).mapIndexed { index, value ->
        value * (13 - index)
    }.sum()

    val checkDigit = (11 - (sum % 11)) % 10

    return digits[12] == checkDigit
}