package io.github.vcuswimlab.stackintheflow.controller;

import com.intellij.openapi.editor.*;
import io.github.vcuswimlab.stackintheflow.controller.info.match.StringMatchUtils;
import io.github.vcuswimlab.stackintheflow.model.score.combiner.Combiner;

<<<<<<< HEAD
=======
import javax.ws.rs.POST;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
>>>>>>> 185f428 (Finished - final push)
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Chase on 1/7/2017.
 */
public class AutoQueryGenerator {

<<<<<<< HEAD
=======

>>>>>>> 185f428 (Finished - final push)
    private static final int MAX_QUERY_TERMS = 4;

    public static String generateQuery(Editor editor, Combiner combiner) {

<<<<<<< HEAD
=======
        LexicalAnalyzer lx = new LexicalAnalyzer();

>>>>>>> 185f428 (Finished - final push)
        CaretModel caretModel = editor.getCaretModel();
        LogicalPosition logicalPosition = caretModel.getLogicalPosition();
        SelectionModel selectionModel = editor.getSelectionModel();

        final Document document = editor.getDocument();

<<<<<<< HEAD
        String selectedText = selectionModel.getSelectedText();

        Map<String, Integer> termsFreqMap = new HashMap<>();

        // If the user has not selected anything then extract query from entire document
        if (selectedText == null || selectedText.trim().isEmpty()) {
            String editorText = document.getText();

            Set<String> imports = StringMatchUtils.extractImports(editorText);

            imports.forEach(i -> Arrays.stream(i.toLowerCase().split("\\."))
                    .forEach(t -> termsFreqMap.put(t, 1 + termsFreqMap.getOrDefault(t, 0))));

            String[] lines = editorText.split("\\n");

            int linePos = logicalPosition.line;
            if (linePos < lines.length) {
                String currentLine = lines[logicalPosition.line];

                Arrays.stream(currentLine.toLowerCase().split("\\b"))
                        .forEach(t -> termsFreqMap.put(t, 2 + termsFreqMap.getOrDefault(t, 0)));
            }
        } else { // The user has highlighted as selection, pull our terms from that
            Arrays.stream(selectedText.toLowerCase().split("\\b"))
                    .forEach(t -> termsFreqMap.put(t, 2 + termsFreqMap.getOrDefault(t, 0)));
        }

        Map<String, Double> scores =
                termsFreqMap.entrySet().stream()
                        .collect(Collectors.toMap(Map.Entry::getKey, e -> combiner.generateCumulativeScore(e.getKey())));

        //Collects the MAX_QUERY_TERMS most frequent elements in the list
        List<String> top = scores
                .entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).limit(MAX_QUERY_TERMS)
                .map(Map.Entry::getKey).collect(Collectors.toList());

        return top.stream().collect(Collectors.joining(" "));
    }
=======

        String selectedText = selectionModel.getSelectedText();
        if (selectedText == null || selectedText.trim().isEmpty()){
            selectedText = document.getText();
        }

        String[] ls = selectedText.split("\\n");
        for (String line: ls){
            lx.analyze(line);
        }

        //TODO: Add Web api
        System.out.println(lx.sb.toString());
        String res = sendPost("http://127.0.0.1:5000/generate_query_by_code_snippet", "code_snippet="+lx.sb.toString());
        System.out.println(res);
        String res_remove_tail = res.substring(0, res.length()-2);
        String query = res_remove_tail.substring(13);
        return query;


//        Map<String, Integer> termsFreqMap = new HashMap<>();
//
//        // If the user has not selected anything then extract query from entire document
//        if (selectedText == null || selectedText.trim().isEmpty()) {
//            String editorText = document.getText();
//
//            Set<String> imports = StringMatchUtils.extractImports(editorText);
//
//            imports.forEach(i -> Arrays.stream(i.toLowerCase().split("\\."))
//                    .forEach(t -> termsFreqMap.put(t, 1 + termsFreqMap.getOrDefault(t, 0))));
//
//            String[] lines = editorText.split("\\n");
//
//            int linePos = logicalPosition.line;
//
//            if (linePos < lines.length) {
//                String currentLine = lines[logicalPosition.line];
//
//                Arrays.stream(currentLine.toLowerCase().split("\\b"))
//                        .forEach(t -> termsFreqMap.put(t, 2 + termsFreqMap.getOrDefault(t, 0)));
//            }
//        } else { // The user has highlighted as selection, pull our terms from that
//            Arrays.stream(selectedText.toLowerCase().split("\\b"))
//                    .forEach(t -> termsFreqMap.put(t, 2 + termsFreqMap.getOrDefault(t, 0)));
//        }
//
//        Map<String, Double> scores =
//                termsFreqMap.entrySet().stream()
//                        .collect(Collectors.toMap(Map.Entry::getKey, e -> combiner.generateCumulativeScore(e.getKey())));
//
//        //Collects the MAX_QUERY_TERMS most frequent elements in the list
//        List<String> top = scores
//                .entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).limit(MAX_QUERY_TERMS)
//                .map(Map.Entry::getKey).collect(Collectors.toList());
//
//        return top.stream().collect(Collectors.joining(" "))+"hehe";
    }
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }

>>>>>>> 185f428 (Finished - final push)
}
