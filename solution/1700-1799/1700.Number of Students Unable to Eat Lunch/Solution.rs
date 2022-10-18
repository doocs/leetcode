impl Solution {
    pub fn count_students(students: Vec<i32>, sandwiches: Vec<i32>) -> i32 {
        let mut count = [0, 0];
        for &v in students.iter() {
            count[v as usize] += 1;
        }
        for &v in sandwiches.iter() {
            let v = v as usize;
            if count[v as usize] == 0 {
                return count[v ^ 1];
            }
            count[v] -= 1;
        }
        0
    }
}
