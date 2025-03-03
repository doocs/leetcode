import pandas as pd


def analyze_dna_patterns(samples: pd.DataFrame) -> pd.DataFrame:
    samples["has_start"] = samples["dna_sequence"].str.startswith("ATG").astype(int)
    samples["has_stop"] = (
        samples["dna_sequence"].str.endswith(("TAA", "TAG", "TGA")).astype(int)
    )
    samples["has_atat"] = samples["dna_sequence"].str.contains("ATAT").astype(int)
    samples["has_ggg"] = samples["dna_sequence"].str.contains("GGG+").astype(int)
    return samples.sort_values(by="sample_id").reset_index(drop=True)
