package ru.kslacker.reelrate.exceptions;

public class ReelRateUserIsNotLinkedException extends ReelRateException {
    public ReelRateUserIsNotLinkedException() {
        super("ReelRate user is not linked to this account");
    }
}
