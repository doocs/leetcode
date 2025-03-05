use std::cmp::Ordering;

impl Solution {
    pub fn reorder_log_files(logs: Vec<String>) -> Vec<String> {
        let mut logs = logs;

        logs.sort_by(|log1, log2| {
            let split1: Vec<&str> = log1.splitn(2, ' ').collect();
            let split2: Vec<&str> = log2.splitn(2, ' ').collect();

            let is_letter1 = split1[1].chars().next().unwrap().is_alphabetic();
            let is_letter2 = split2[1].chars().next().unwrap().is_alphabetic();

            if is_letter1 && is_letter2 {
                let cmp = split1[1].cmp(split2[1]);
                if cmp != Ordering::Equal {
                    return cmp;
                }
                return split1[0].cmp(split2[0]);
            }

            if is_letter1 {
                Ordering::Less
            } else if is_letter2 {
                Ordering::Greater
            } else {
                Ordering::Equal
            }
        });

        logs
    }
}
