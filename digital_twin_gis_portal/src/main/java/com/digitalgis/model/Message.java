package com.digitalgis.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.core.io.FileSystemResource;

public class Message implements Serializable {

	private static final long serialVersionUID = 1L;

	private String to;
	private String subject;
	private String cc;
	private String bcc;
	private boolean isAttachementAvailable;
	private List<FileSystemResource> fileSystemResources;
	private List<String> texts;

	public Message() {
	}

	public Message(String to, String subject, boolean isAttachementAvailable,
			List<FileSystemResource> fileSystemResources, List<String> texts) {
		super();
		this.to = to;
		this.subject = subject;
		this.isAttachementAvailable = isAttachementAvailable;
		this.fileSystemResources = fileSystemResources;
		this.texts = texts;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getBcc() {
		return bcc;
	}

	public void setBcc(String bcc) {
		this.bcc = bcc;
	}

	public boolean isAttachementAvailable() {
		return isAttachementAvailable;
	}

	public void setAttachementAvailable(boolean isAttachementAvailable) {
		this.isAttachementAvailable = isAttachementAvailable;
	}

	public List<FileSystemResource> getFileSystemResources() {
		return fileSystemResources;
	}

	public void setFileSystemResources(List<FileSystemResource> fileSystemResources) {
		this.fileSystemResources = fileSystemResources;
	}

	public List<String> getTexts() {
		return texts;
	}

	public void setTexts(List<String> texts) {
		this.texts = texts;
	}

	@Override
	public String toString() {
		return "Message [to=" + to + ", subject=" + subject + ", cc=" + cc + ", bcc=" + bcc
				+ ", isAttachementAvailable=" + isAttachementAvailable + ", fileSystemResources=" + fileSystemResources
				+ ", texts=" + texts + "]";
	}
}
