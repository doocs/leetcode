object Solution {
    def mergeTriplets(triplets: Array[Array[Int]], target: Array[Int]): Boolean = {
        val Array(x, y, z) = target
        var (d, e, f) = (0, 0, 0)
        
        for (Array(a, b, c) <- triplets) {
            if (a <= x && b <= y && c <= z) {
                d = d.max(a)
                e = e.max(b)
                f = f.max(c)
            }
        }
        
        d == x && e == y && f == z
    }
}