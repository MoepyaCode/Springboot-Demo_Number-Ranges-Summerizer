import axios from "axios";

const BASE_URL = "http://localhost:8081";

export const postNumbersList = async (input: string): Promise<string> => {
  const response = await axios.post(`${BASE_URL}/api/ranges/summarize`, { input });
  return response.data as string;
};