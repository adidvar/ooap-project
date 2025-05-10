```mermaid
stateDiagram-v2

[*] --> Created : created()
Created: entry / log("Created")
Created --> Scheduled : schedule(date)
Created --> Canceled : cancel()

Scheduled --> Snoozed : snooze()
Scheduled --> Sending : trigger() [timeReached()]
Scheduled --> Canceled : cancel()
Snoozed --> Scheduled : resume()

Sending --> Sent : send() [success]
Sending --> Failed : send() [fail]
Failed --> RetryScheduled : retry()
RetryScheduled --> Sent : send() [success]
RetryScheduled --> Failed : send() [fail again]

Sent --> Archived : archive()
Failed --> Archived : archive()
Canceled --> [*]
Archived --> [*]

