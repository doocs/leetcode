SELECT Name
FROM Candidate,
    (SELECT CandidateId,
         count(*) AS total
    FROM Vote
    GROUP BY  CandidateId
    ORDER BY  total DESC limit 1) AS tmp
WHERE Candidate.id = tmp.CandidateId
