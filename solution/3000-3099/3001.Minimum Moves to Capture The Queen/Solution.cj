class Solution {
    func minMovesToCaptureTheQueen(a: Int64, b: Int64, c: Int64, d: Int64, e: Int64, f: Int64): Int64 {
        if (a == e && (c != a || (d - b) * (d - f) > 0)) {
            return 1
        }
        if (b == f && (d != b || (c - a) * (c - e) > 0)) {
            return 1
        }
        if (c - e == d - f && (a - e != b - f || (a - c) * (a - e) > 0)) {
            return 1
        }
        if (c - e == f - d && (a - e != f - b || (a - c) * (a - e) > 0)) {
            return 1
        }
        2
    }
}
