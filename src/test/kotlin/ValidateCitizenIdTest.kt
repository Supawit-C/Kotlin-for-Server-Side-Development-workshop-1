import org.example.validateCitizenId
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class ValidateCitizenIdTest {

    @Test
    fun `valid 13 digit id returns true`() {
        val id = "1234567890121"
        val result = validateCitizenId(id)
        assertTrue(result)
    }

    @Test
    fun `id with wrong length returns false`() {
        val id = "1234567890"
        val result = validateCitizenId(id)
        assertFalse(result)
    }

    @Test
    fun `id with letters returns false`() {
        val id = "123b456a7890c"
        val result = validateCitizenId(id)
        assertFalse(result)
    }

    @Test
    fun `id with wrong checksum returns false`() {
// หลักที่ 13 ต้องเป็น check digit ที่คำนวณจาก 12 หลักแรก
// 110170018520 → check digit ที่ถูกต้องคือ 6
        assertFalse(validateCitizenId("1101700185207")) // หลักสุดท้ายผิด
        assertFalse(validateCitizenId("1234567890129")) // ที่ถูกคือ ...1

// ใบที่ checksum ถูกต้อง ต้องยังผ่านอยู่
        assertTrue(validateCitizenId("3509900547250"))
        assertTrue(validateCitizenId("1234567890121"))
    }

    @Test
    fun`id with Thai number returns true`(){
        assertTrue { validateCitizenId("๑๒๓๔๕๖๗๘๙๐๑๒๑") }
    }
}