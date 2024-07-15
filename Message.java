public class Message {
    private Parrot sender;
    private Parrot receiver;
    private String content;

    public Message(Parrot sender, Parrot receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
    }

    public Parrot getSender() { return sender; }
    public void setSender(Parrot sender) { this.sender = sender; }
    public Parrot getReceiver() { return receiver; }
    public void setReceiver(Parrot receiver) { this.receiver = receiver; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    @Override
    public String toString() {
        return "Message{" +
                "sender=" + sender.getName() +
                ", receiver=" + receiver.getName() +
                ", content='" + content + '\'' +
                '}';
    }
}

