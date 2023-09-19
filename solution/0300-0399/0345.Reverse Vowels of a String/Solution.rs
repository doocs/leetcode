impl Solution {
    pub fn reverse_vowels(s: String) -> String {
        let vowel = String::from("aeiouAEIOU");
        let mut data: Vec<char> = s.chars().collect();
        let (mut i, mut j) = (0, s.len() - 1);
        while i < j {
            while i < j && !vowel.contains(data[i]) {
                i += 1;
            }
            while i < j && !vowel.contains(data[j]) {
                j -= 1;
            }
            if i < j {
                data.swap(i, j);
                i += 1;
                j -= 1;
            }
        }
        data.iter().collect()
    }
}
