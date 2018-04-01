package io.rogermoore.poc.domain;

import io.rogermoore.poc.domain.type.PaymentStatus;

import java.util.UUID;

public class Payment {

    private final UUID paymentId;

    private final Double amount;

    private final String sender;

    private final String recipient;

    private PaymentStatus status =  PaymentStatus.INITIATED;

    public Payment() {
        this.paymentId = null;
        this.amount = 0.0;
        this.sender = null;
        this.recipient = null;
    }

    public Payment(UUID paymentId, Double amount, String sender, String recipient ) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.sender = sender;
        this.recipient = recipient;
    }

    public UUID getPaymentId() {
        return paymentId;
    }

    public Double getAmount() {
        return amount;
    }

    public String getSender() {
        return sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus( PaymentStatus status ) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder( "[ " );

        builder.append( "payment_id: '" ).append( paymentId.toString() ).append( "', " );
        builder.append( "amount: '" ).append( amount.toString() ).append( "', " );
        builder.append( "sender: '" ).append( sender ).append( "', " );
        builder.append( "recipient: '" ).append( recipient ).append( "', " );
        builder.append( "status: '" ).append( status.toString() ).append( "' ]" );

        return builder.toString();
    }

}
