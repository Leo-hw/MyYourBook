package pack.model;

import java.util.ArrayList;

public interface SearchInter {
	ArrayList<NewBookDto> getDataAll(String type, String search);
}
