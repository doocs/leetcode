impl Solution {
    pub fn furthest_distance_from_origin(moves: String) -> i32 {
        let l = moves.chars().filter(|&c| c == 'L').count() as i32;
        let r = moves.chars().filter(|&c| c == 'R').count() as i32;
        let blank = moves.chars().filter(|&c| c == '_').count() as i32;
        (l - r).abs() + blank
    }
}
