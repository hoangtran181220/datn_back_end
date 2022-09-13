package do_an.common;//package com.viettel.vhkt.common;
//
//import com.jcraft.jsch.*;
//import com.viettel.vhkt.service.sync.SyncService;
//import lombok.extern.log4j.Log4j2;
//import org.apache.commons.httpclient.HttpClient;
//import org.apache.commons.httpclient.HttpConnectionManager;
//import org.apache.commons.httpclient.HttpException;
//import org.apache.commons.httpclient.methods.PostMethod;
//import org.apache.commons.httpclient.methods.RequestEntity;
//import org.apache.commons.httpclient.methods.StringRequestEntity;
//import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import utils.Protocol;
//
//import java.io.*;
//import java.util.Base64;
//import java.util.Date;
//import java.util.logging.Logger;
//
//@Component
//@Log4j2
//public class SSHUploadFile {
//    @Value("${config.account}")
//    private String account;
//    @Value("${config.userLogin}")
//    private String userLogin;
//    @Value("${config.urlUpdateGnoc}")
//    private String urlUpdateGNOC;
//    @Value("${config.userNameSOAP}")
//    private String userNameSOAP;
//    @Value("${config.passwordSOAP}")
//    private String passwordSOAP;
//
//    private final int CHANNEL_TIMEOUT = 5000;
//    @Autowired
//    private RequestSettingRepository rsRepository;
//    @Autowired
//    private SyncService syncService;
//    @Autowired
//    private HistorySettingRepository historySettingRepository;
//
//    public boolean putFile(String localFile, String fileName, String userName, String host, int port, String pass, String copyPath, RequestSetting req, Logger logger) throws IOException {
//        Session session = null;
//        ChannelExec channel = null;
//        FileInputStream localInput = null;
//        try {
//            JSch jsch = new JSch();
//            session = jsch.getSession(userName, host, port);
//            session.setPassword(pass);
//            session.setConfig("StrictHostKeyChecking", "no");
//            session.connect();
//
//            // Execute command
//            channel = (ChannelExec) session.openChannel("exec");
//            channel.setCommand("pwd");
//            ByteArrayOutputStream responseStream = new ByteArrayOutputStream();
//            channel.setOutputStream(responseStream);
//            channel.connect();
//
//            while (channel.isConnected()) {
//                Thread.sleep(100);
//            }
//            // output path when connect
//            String responseString = new String(responseStream.toByteArray());
//            responseString = responseString.replaceAll("\n", "");
//            System.out.println(responseString);
//
//            localInput = new FileInputStream(localFile);
//            System.out.println(localInput);
//            try {
//                ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
//                channelSftp.connect();
////                channelSftp.put(localInput, responseString + "/" + copyPath + "/" + fileName);
////                fake folder
////                    todo: tulv comment
//                copyPath = "/anhnv185/anhnv185";
//                channelSftp.put(localInput, copyPath + "/" + fileName);
//                channelSftp.disconnect();
//            } catch (Exception e) {
//                logger.info("\n" + e);
//                setRequestSettingStatus(req, Constants.APP_PARAMS.CONNECT_SERVER_STATUS.NO_CONNECT_STATUS, Constants.APP_PARAMS.STATUS_SETTING.NOT_SETUP);
//                System.out.println(e.getMessage());
//                return false;
//            }
//            logger.info("Copy file: " + fileName + " success");
//            channel.disconnect();
//            session.disconnect();
//            localInput.close();
//        } catch (JSchException | InterruptedException | IOException e) {
//            logger.info("\n" + e);
//            setRequestSettingStatus(req, Constants.APP_PARAMS.CONNECT_SERVER_STATUS.NO_CONNECT_STATUS, Constants.APP_PARAMS.STATUS_SETTING.NOT_SETUP);
//            System.out.println("\n" + e);
//            channel.disconnect();
//            session.disconnect();
//            localInput.close();
//            return false;
//        }
//        return true;
//    }
//
//    public int sendRequestUpdateSR(RequestSetting req, FileWriter writer) throws IOException {
//        PostMethod post = null;
////        writer.write("Start send request update SR");
//        String response;
//        String request = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.sr.gnoc.viettel.com/\">"
//                + "<soapenv:Header/>"
//                + "<soapenv:Body>"
//                + " <ser:updateSR>"
//                + "<srDTO>"
//                + "<srCode>" + req.getCode() + "</srCode>"
//                + "<status>" + req.getStatusSetting() + "</status>"
//                + "<actionType>UPDATE</actionType>"
//                + "<account>" + account + "</account>"
//                + "<userLogin>" + userLogin + "</userLogin>"
//                + "</srDTO>"
//                + "</ser:updateSR>"
//                + "</soapenv:Body>"
//                + "</soapenv:Envelope>";
//        try {
//            System.out.println("Req: " + request);
//            //writer.write("Start send sync gnoc request ***************************");
//            System.out.println("Call IP: " + urlUpdateGNOC);
//            Protocol protocol = new Protocol(urlUpdateGNOC);
//            HttpClient httpClient = new HttpClient();
//            HttpConnectionManager conMgr = httpClient.getHttpConnectionManager();
//            HttpConnectionManagerParams conPars = conMgr.getParams();
//            conPars.setConnectionTimeout(180000);
//            conPars.setSoTimeout(180000);
//            post = new PostMethod(protocol.getUrl());
//
//            System.out.println("Protocal URL: " + protocol.getUrl());
//            RequestEntity entity = new StringRequestEntity(request, "text/xml", "UTF-8");
//            post.setRequestEntity(entity);
//            String encoding = Base64.getEncoder().encodeToString((userNameSOAP + ":" + passwordSOAP).getBytes());
//            post.setRequestHeader("Authorization", "Basic " + encoding);
//            httpClient.executeMethod(post);
//            InputStream is = post.getResponseBodyAsStream();
//
//            if (is != null) {
//                response = SyncService.getStringFromInputStream(is);
//                System.out.println("Response REQUEST UPDATE SR: " + response);
//            }
//
//        } catch (IllegalArgumentException e) {
////            writer.write("Error send Request to GNOC: " + e);
////            writer.close();
//            return -1;
//        } catch (HttpException e) {
////            writer.write("Error send Request to GNOC: " + e);
////            writer.close();
//            return -1;
//        } catch (IOException e) {
////            writer.write("Error send Request to GNOC: " + e);
////            writer.close();
//            return -1;
//        } finally {
//            if (post != null) {
//                post.releaseConnection();
//            }
//        }
////        writer.write("Finish send request update SR");
//        return 0;
//    }
//
//    public void createHistorySetting(RequestSetting req, int isConnect, String createBy) {
//        HistorySetting historySetting = new HistorySetting();
//        historySetting.setConnectServer(isConnect);
//        historySetting.setRequestId(req.getId());
//        historySetting.setCreateDate(new Date());
//        historySetting.setCreateBy(createBy);
//        historySetting.setInventoryFileId(req.getInventoryFileId());
//        historySetting.setPlaybookFileId(req.getPlaybookFileId());
//        historySetting.setPlaybookUnsId(req.getPlaybookUnsId());
//        historySetting.setResultInstallFileId(req.getResultInstallFileId());
//        historySettingRepository.save(historySetting);
//    }
//
//    public void excuteCommand(ChannelExec channel, String pass, String homePath, String type, String command, Logger logger) throws JSchException {
//        logger.info("Running command: " + command);
//        channel.setCommand("cd " + homePath + " && " + command);
//        channel.setErrStream(System.err);
//        try (OutputStream outputStream = channel.getOutputStream()) {
//            channel.setPty(true);
//            channel.connect(CHANNEL_TIMEOUT);
//
//            outputStream.write((pass + "\n").getBytes()); //$NON-NLS-1$
//            outputStream.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void setRequestSettingStatus(RequestSetting req, int connectSV, int statusSetting) {
//        req.setConnectServer(connectSV);
//        req.setStatusSetting(statusSetting);
//        rsRepository.save(req);
//    }
//
//    public int writeLog(ChannelExec channel, FileWriter writer, RequestSetting req) throws IOException {
//        InputStream in = channel.getInputStream();
//        byte[] tmp = new byte[1000];
//        boolean isSuccess = false;
//
//        while (true) {
//            while (in.available() > 0) {
//                int i = in.read(tmp, 0, 1000);
//                if (i < 0) break;
//                String line = new String(tmp, 0, i);
//                writer.write(line);
//                if (line.contains("ok=") && !line.contains("ok=0") && line.contains("failed=") && line.contains("failed=0")) {
//                    isSuccess = true;
//                }
//            }
//            if (channel.isClosed()) {
//                break;
//            }
//        }
//        in.close();
//        if (!isSuccess) {
//            setRequestSettingStatus(req, Constants.APP_PARAMS.CONNECT_SERVER_STATUS.CONNECT_STATUS, Constants.APP_PARAMS.STATUS_SETTING.FAILED);
//            writer.write("\nSetup failed\n");
//            writer.flush();
//            writer.close();
//            return -1;
//        }
//
//        return 0;
//    }
//
//    public int checkTelnet(ChannelExec channel, FileWriter writer, RequestSetting req) throws IOException {
//        InputStream in = channel.getInputStream();
//        byte[] tmp = new byte[1000];
//
//        while (true) {
//            while (in.available() > 0) {
//                int i = in.read(tmp, 0, 1000);
//                if (i < 0) break;
//                String line = new String(tmp, 0, i);
//                writer.write(line);
//                if (line.contains("FAILED!")) {
//                    return -1;
//                }
//            }
//            if (channel.isClosed()) {
//                break;
//            }
//        }
//        in.close();
//        return 0;
//    }
//}
