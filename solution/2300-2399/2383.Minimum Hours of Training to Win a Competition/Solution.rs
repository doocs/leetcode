impl Solution {
    pub fn min_number_of_hours(
        mut initial_energy: i32,
        mut initial_experience: i32,
        energy: Vec<i32>,
        experience: Vec<i32>,
    ) -> i32 {
        let n = energy.len();
        let mut res = 0;
        for i in 0..n {
            if initial_energy <= energy[i] {
                res += energy[i] - initial_energy + 1;
                initial_energy = energy[i] + 1;
            }
            if initial_experience <= experience[i] {
                res += experience[i] - initial_experience + 1;
                initial_experience = experience[i] + 1;
            }
            initial_energy -= energy[i];
            initial_experience += experience[i];
        }
        res
    }
}
