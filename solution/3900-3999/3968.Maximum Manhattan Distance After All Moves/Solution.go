func maxDistance(moves string) int {
    x, y, z := 0, 0, 0
    for _, c := range moves {
        if c == 'U' {
            x -= 1
        } else if c == 'D' {
            x += 1
        } else if c == 'L' {
            y -= 1
        } else if c == 'R' {
            y += 1
        } else {
            z += 1
        }
    }
    return abs(x) + abs(y) + z
}

func abs(x int) int {
    if x < 0 {
        return -x
    }
    return x
}